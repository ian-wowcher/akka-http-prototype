package model;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * TODO make immutable
 * */
public class DealsListing {

    /* See DealVoucherSite.hbm.xml*/

    private static final String SITE_PATH = "sitePath";

    private static final String TITLE = "title";
    private static final String DEAL_PRODUCT = "DEAL_PRODUCT";
    private static final String DESCRIPTION = "description";

    private static final String BUSINESS = "businessName";
    private static final String GET_DEALS_BY_LOCATION =
            "select dv.id, " +
                    "dv.deal_product as "+ DEAL_PRODUCT +", " +
                    "PRODUCT.title as "+ TITLE +", " +
                    "PRODUCT.description as "+ DESCRIPTION +", " +
                    "business.name as "+ BUSINESS +
            " from DEAL_VOUCHER dv" +
                  " left join DEAL_VOUCHER_SITE dvs on dvs.deal_voucher_id = dv.id\n" +
                  " left join PRODUCT on PRODUCT.id = dv.id\n" +
                  " left join SITE on site.id = dvs.site_id\n" +
                  " left join BUSINESS on business.id = dv.business_id" +
            " where " +
                  " site.site_path =:" + SITE_PATH + " " +
                  " and dv.start_date <= sysdate" +
                  " and dv.closing_date >= sysdate" +
                  " and PRODUCT.status_id = 1" +
                  " and dv.always_on = 'N'" +
            " order by dv.start_date desc";

    public ListDealsOptions filter;
    public int total;
    public int totalPages;
    public final List<Deal> deals;

    public DealsListing(List<Deal> deals, int totalPages, int total, ListDealsOptions filter) {
        this.deals = deals;
        this.totalPages = totalPages;
        this.total = total;
        this.filter = filter;
    }

    public DealsListing(List<Deal> deals) {
        this.deals = deals;
    }

    public static DealsListing getByLocation(String location) {

        DBI dbi = new DBI(DailyDealsDataSource.getInstance());
        Handle h = dbi.open();
        final List<Deal> dealList = h.createQuery(GET_DEALS_BY_LOCATION)
        .bind(SITE_PATH, location)
        .map(new DealMapper())
        .list();
        h.close();
        return new DealsListing(dealList);
    }

    private static class DealMapper implements ResultSetMapper<Deal> {

        @Override
        public Deal map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

            String deal_product = resultSet.getString(DEAL_PRODUCT);
            String title = resultSet.getString(TITLE);
            String description = resultSet.getString(DESCRIPTION);
            String business = resultSet.getString(BUSINESS);

            return new Deal(
                    deal_product,
                    title,
                    description,
                    business
            );
        }
    }
}